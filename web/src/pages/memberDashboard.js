import XchangeClient from '../api/xchangeClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/*
The code below this comment is equivalent to...
const EMPTY_DATASTORE_STATE = {
    'search-criteria': '',
    'search-results': [],
};

...but uses the "KEY" constants instead of "magic strings".
The "KEY" constants will be reused a few times below.
*/

const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};


/**
 * Logic needed for the view playlist page of the website.
 */
class SelectedItem extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        this.table = new Table(this.dataStore)
        console.log("selectedItem constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
    //bring in the member , get their email
        const searchCriteria = this.dataStore.get(member);

    //make a call to api getMemberListings
        if (searchCriteria != null) {
            const results = await this.client.getMemberListings(searchCriteria.email);
            console.log("MemberDashboard coming back from client, results: " + results);

            this.dataStore.setState({
                [SEARCH_CRITERIA_KEY]: searchCriteria,
                [SEARCH_RESULTS_KEY]: results,
            });
        } else {
            this.dataStore.setState(EMPTY_DATASTORE_STATE);
        }
    }
    // New method: display items: for each listing, create a div, with details. Preferrably a table.
    //



    /**
     * Add the header to the page and load the MusicPlaylistClient.
     * MY NOTES: I think this one is reloading the page when someone wants to add a song. It's mean for an update
     * to save the new state of the item. I don't think I need it.
     */
    mount() {
//        document.getElementById('add-song').addEventListener('click', this.addSong);

//        document.getElementById('email-button').addEventListener('click', function() {
//         const emailButton = document.getElementById('email-button');
//         emailButton.href = 'mailto:' + item.email;
//         Console.log("item.email is: " + item.email);
        this.header.addHeaderToPage();
        this.table.addTableToPage();
        this.client = new XchangeClient();
        this.clientLoaded();
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const selectedItem = new SelectedItem();
    selectedItem.mount();
};

window.addEventListener('DOMContentLoaded', main);