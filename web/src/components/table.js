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
 
      const exchangedFilter = document.getElementById('exchanged-filter')
    //   const tableRows = document.querySelectorAll('#table-container tbody tr')
    //     console.log("befoe listener, tableRows: " + tableRows);
      exchangedFilter.addEventListener('change', function() {
        console.log("entering event change listener");
        const isChecked = this.checked;
        console.log("isChecked: " + isChecked);
        var table = document.getElementById("table-id");
        console.log("table is: " + table);
        // if(isChecked == 'true') {
            for (var i =0, row; row = table.rows[i]; i++ ) {
                //iterate through rows
                console.log("current row is: " + row);
                for(var j = 0, col; col = row.cells[j]; j++) {
                    //iterate through columns
                    //columns will be accessed using the 'col' variable assign inthe for loop
                    //if col.value == true add hidden class to the row. 
                    console.log("column currently on: " + col.innerText);
                    if(col.innerText == 'true') {
                        console.log("assigning hidden class.");
                        row.classList.add('hidden');
                    }
                }
            }
        // } 
        // else if(isChecked == 'false') {
        //     for (var i =0, row; row = table.rows[i]; i++ ) {
        //         //iterate through rows
        //         console.log("current row is: " + row);
        //         for(var j = 0, col; col = row.cells[j]; j++) {
        //             //iterate through columns
        //             //columns will be accessed using the 'col' variable assign inthe for loop
        //             //if col.value == true add hidden class to the row. 
        //             console.log("column currently on: " + col.innerText);
        //             if(col.innerText == 'true') {
        //                 console.log("removing hidden class.");
        //                 row.classList.remove('hidden');
        //             }
        //         }
        //     }
        // }
      });
    }

    


  async addTableToPage() {
      console.log('Table.js building...');

    try {
        const listings = await this.client.getMemberListings();
        const table = this.buildTable(listings);
        const container = document.getElementById('table-container');
        table.classList.add('table-container'); 
        container.appendChild(table);
       //begin creating toggle switch
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
      table.classList.add('table-container');
      table.setAttribute('id', 'table-id');

      // Create the table header row
      console.log("going to create table now.");
      const headerRow = table.insertRow();
      const headers = ['Title', 'Description', 'Exchanged', 'Edit'];
      headers.forEach(header => {
          const th = document.createElement('th');
          th.innerText = header;
          headerRow.appendChild(th);
      });

      //Reveal the toggle button
      const toggle = document.getElementById("exchanged-filter");
      toggle.classList.remove("hidden");


      // Create the table body rows
      console.log("data received: " + data);
      data.forEach(item => {
            console.log("item: " + item);
          const row = table.insertRow();
          row.classList.add('playlist-row'); 
       
        const editButton = this.createEditButton(item);
          const cells = [item.title, item.description, item.exchanged, editButton];
          cells.forEach(cell => { 
            //I wanted to change false to Nope and True to Yep but couodn't do it.
                // if (item.exchanged === true) {
                //     const changeThis = row.cells[3]; 
                //     changeThis.innerText = "Yep";
                //     // const td = document.createElement('td');
                //     // td.innerText = "Yep";
                //     // row.appendChild(td);
                //     // row.
                //   } 
                //   else {
                //     const td = document.createElement('td');
                //     td.innerText = "Nope";
                //     row.appendChild(td);
                //   }
            
            if (cell == editButton){
                const itemLink = document.createElement('a');
                itemLink.id = 'edit-button';
                itemLink.className= 'button';
                itemLink.href = '/updateListing.html?itemId=' + item.itemId;
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
    itemLink.href = '/updateListing.html?itemId=' + item.itemId;
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

filterExchanged(item, command) {
    if (command === 'hide') {
      const cells = [item.exchanged];
      cells.forEach(cell => {
        if (cell === true) {
          itemLink.classList.add('hide');
        }
      });
    }
  }
  


}