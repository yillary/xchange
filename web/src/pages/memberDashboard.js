import XchangeClient from '../api/xchangeClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";
import Table from '../components/table';


const SEARCH_CRITERIA_KEY = 'search-criteria';
const SEARCH_RESULTS_KEY = 'search-results';
const EMPTY_DATASTORE_STATE = {
    [SEARCH_CRITERIA_KEY]: '',
    [SEARCH_RESULTS_KEY]: [],
};


/**
 * Logic needed for the view table page of the website.
 */
class MemberDashboard extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount'], this);

        // Create a enw datastore with an initial "empty" state.
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.table = new Table(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
        this.header = new Header();
    }

    /**
     * Add the table to the page and load the MusicPlaylistClient.
     */


    /**
     * Add the table to the page and load the XchangeClient
     */
    async mount() {
        console.log('MemberDashboard.js mounting...');
        this.table.addTableToPage();
        this.client = new XchangeClient();
        this.header.addHeaderToPage();
//        const member = await this.client.getIdentity();
//        const listings = await this.client.getMemberListings(member.email);
//        const listings = await this.client.getMemberListings();
        //build table will retrieve the data forme.
        this.header.addHeaderToPage;
    }
}

    const main = async () => {
        const memberDashboard = new MemberDashboard();
        memberDashboard.mount();
    };

    window.addEventListener('DOMContentLoaded', main);
