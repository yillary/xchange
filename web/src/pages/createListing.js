import XchangeClient from '../api/xchangeClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
import Authenticator from '../api/authenticator';
import { Auth } from 'aws-amplify';

/**
 * Logic needed for the create playlist page of the website.
 */
class CreateListing extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'submit', 'redirectToViewMemberDashboard'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewMemberDashboard);
        this.header = new Header(this.dataStore);
        this.authenticator = new Authenticator();
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */
    mount() {
        document.getElementById('create').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new XchangeClient();
    }

    /**
     * Method to run when the create listing submit button is pressed. Call the XchangeService to create the
     * Listing.
     */
    async submit(evt) {
        evt.preventDefault();

        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');

        const createButton = document.getElementById('create');
        const origButtonText = createButton.innerText;
        createButton.innerText = 'Loading...';

        const listingTitle = document.getElementById('create-title').value;
        const description = document.getElementById('create-description').value;
        const zipCode = document.getElementById('create-zipCode').value;
        const selectedType = document.querySelector('input[name="type_selector"]:checked');
        const selectedTypeValue = selectedType.value; 

        // let tags;
        // if (tagsText.length < 1) {
        //     tags = null;
        // } else {
        //     tags = tagsText.split(/\s*,\s*/);
        // }

        const listing = await this.client.createListing(listingTitle, description, selectedTypeValue, zipCode, (error) => {
            createButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
        });
        this.dataStore.set('listing', listing);
    }

    /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     */
    redirectToViewMemberDashboard() {
        //get current user info from authenticator
        //redirect to memberDashboard.
        const user = this.authenticator.getCurrentUserInfo;
        if (user != null) {
            window.location.href = `/memberDashboard.html?id=${user.email}`;
        }
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createListing = new CreateListing();
    createListing.mount();
};

window.addEventListener('DOMContentLoaded', main);