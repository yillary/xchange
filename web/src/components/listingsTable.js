import MusicPlaylistClient from '../api/musicPlaylistClient';
import BindingClass from "../util/bindingClass";

/**
 * The order table component for the website.
 */
export default class OrderTable extends BindingClass {
    constructor() {
        super();

        const methodsToBind = ['addGridToPage', 'buildGrid'];
        this.bindClassMethods(methodsToBind, this);

        this.client = new xchangeClient();
    }

    async addGridToPage() {
        console.log('listingsTable.js building...');
//        const currentUser = await this.client.getIdentity();
//        const data = await this.client.getAllOrderData();
        const grid = this.buildGrid(data);
        const container = document.getElementById('order-table-container');
        grid.classList.add('grid'); // Add a class to style the table
        container.appendChild(grid);
    }

    buildGrid(data) {
//        if (!Array.isArray(data)) {
//            console.error('Error: data is not an array!');
//            return;
//        }

        const grid = document.createElement('grid');
        grid.classList.add('grid-full');

        // Create the table header row
        const headerRow = table.insertRow();
        const headers = ['Order Id', 'Customer Name', 'Order Items'];
        headers.forEach(header => {
            const th = document.createElement('th');
            th.innerText = header;
            headerRow.appendChild(th);
        });

        // Create the order table body rows and only show unprocessed requests
        data.filter(item => !item.orderProcessed).forEach(item => {
            const row = table.insertRow();
            row.classList.add('order-row');
            const cells = [item.orderId, item.clientId, item.orderItems];
            cells.forEach(cell => {
                const td = document.createElement('td');
                td.innerText = cell;
                row.appendChild(td);
            });
        });

        return table;
    }



}