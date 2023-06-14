import XchangeClient from '../api/xchangeClient';
import BindingClass from "../util/bindingClass";

/**
* The table component for the website.
*/
export default class Table extends BindingClass {
  constructor() {
      super();

      const methodsToBind = ['addTableToPage', 'buildTable'];
      this.bindClassMethods(methodsToBind, this);

      this.client = new XchangeClient();
  }

  async addTableToPage() {
      console.log('Table.js building...');

    try {
        const listings = await this.client.getMemberListings();
        const table = this.buildTable(listings);
        const container = document.getElementById('table-container');
        table.classList.add('table-container'); // Add a class to style the table
        container.appendChild(table);
        toggleExchanged.classList.remove('hidden');
        this.createToggleSlider();
        // const toggleExchanged = document.getElementById('toggle-exchanged');
        // toggleExchanged.classList.remove('hidden');
        // this.createToggleSlider();
    } catch (error) {
        // const toggleExchanged = document.getElementById('toggle-exchanged');
        // toggleExchanged.classList.add('hidden');
        toggleExchanged.classList.add('hidden');
        const note = document.createElement('h3');
        note.innerText = "Hmm . . . nothing's here yet. Let's get started!";
        document.body.appendChild(note);
        const itemLink = document.createElement('a');
        itemLink.className = 'button';
        itemLink.href = '/createItem.html';
        itemLink.innerText = "Post a New Item"
        document.body.appendChild(itemLink);
    }
  }

  buildTable(data) {
      if (!Array.isArray(data)) {
          console.error('Error: data is not an array!');
          return;
      }
      if(data.length == 0) {
        console.error('data is an array with size 0');
      }
      console.log("data length is: " + data.length);
      const table = document.createElement('table');
      table.classList.add('table-container'); // Add a class to style the table

      // Create the table header row
      console.log("going to create table now.");
      const headerRow = table.insertRow();
      const headers = ['Title', 'Description', 'Exchanged', 'Edit'];
      headers.forEach(header => {
          const th = document.createElement('th');
          th.innerText = header;
          headerRow.appendChild(th);
      });

      // Create the table body rows
      console.log("data received: " + data);
      data.forEach(item => {
            console.log("item: " + item);
          const row = table.insertRow();
          row.classList.add('playlist-row'); // Add a class to style the row
       
        const editButton = this.createEditButton(item);
          const cells = [item.title, item.description, item.exchanged, editButton];
          cells.forEach(cell => { 
            if (cell == editButton){
                const itemLink = document.createElement('a');
                itemLink.id = 'edit-button';
                itemLink.className= 'button';
                itemLink.href = '/updateItem.html?itemId=' + item.itemId;
                itemLink.innerText = 'Edit';
                const td = document.createElement('td');
                td.appendChild(itemLink);
                row.appendChild(td);
            } else {
                const td = document.createElement('td');
                td.innerText = cell;
                row.appendChild(td);
            }
          });
      });

      return table;
  }


createEditButton(item) {
    const itemLink = document.createElement('a');
   itemLink.id = document.createElement('id');
    itemLink.href = '/updateItem.html?itemId=' + item.itemId;
}

/**
 * creates a slider button to filter exchanged items.
 */
createToggleSlider() {
    const toggle = document.querySelector.querySelector('.toggle input');
    toggle.addEventListener('click', () => {
        const onOff = toggle.partentNode.querySelector('.onoff');

        onOff.textContent = toggle.checked ? 'Show Exchanged' : 'Hide Exchanged';
    })

}

}