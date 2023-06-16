import XchangeClient from '../api/xchangeClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';
import Authenticator from '../api/authenticator';
import { Auth } from 'aws-amplify';

/**
 * Logic needed for the create playlist page of the website.
 */
class UpdateListing extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'submit', 'redirectToViewMemberDashboard'], this);
        this.dataStore = new DataStore();
        // this.dataStore.addChangeListener(this.redirectToViewMemberDashboard);
        this.header = new Header(this.dataStore);
        this.authenticator = new Authenticator();
    }


    /**
     * Once the client is loaded, get the data from the item in the url.
     */
    async clientLoaded() {
        console.log("hello from clientLoaded()");
        const urlParams = new URLSearchParams(window.location.search);
        const itemId = urlParams.get('itemId');
        //document.getElementById('item-title').innerText = "Loading Playlist ...";

        //making a call to the API for the item's info
        const item = await this.client.getItem(itemId);
        this.dataStore.set('item', item);
        document.getElementById('update-title').value = item.title;
        document.getElementById('update-description').value = item.description;
        document.getElementById('update-zipCode').value = item.zipCode;
    }

    /**
     * Add the header to the page and load the XchangeClient.
     */
    mount() {
        document.getElementById('update').addEventListener('click', this.submit);

        this.header.addHeaderToPage();

        this.client = new XchangeClient();
        this.clientLoaded();
    }

    /**
     * Method to run when the create listing submit button is pressed. Call the XchangeService to create the
     * Listing.
     */
    async submit(evt) {
        evt.preventDefault();
        console.log("hello from UpdateItem.submit()");
        //prep for an error message should one occur
        const errorMessageDisplay = document.getElementById('error-message');
        errorMessageDisplay.innerText = ``;
        errorMessageDisplay.classList.add('hidden');


        console.log("phase one");

        const updateButton = document.getElementById('update');
        const origButtonText = updateButton.innerText;
        updateButton.innerText = 'Got it!';

        console.log("phase two");

        //retrieving our parameters to pass on to the API
        const title = document.getElementById('update-title').value;
        const description = document.getElementById('update-description').value;
        const zipCode = document.getElementById('update-zipCode').value;
        const exchanged = document.querySelector('input[name="exchanged_selector"]:checked');
        const exchangedValue = exchanged.value;

        // if (!exchanged) {
        //     exchanged.value = false;
        // } else {
        //     exchanged.value = true;
        // }

        // console.log("value of title, desc, zip:", listingTitle, description, zipCode, exchangedValue);

        console.log("phase three");

        //make a call to the API, proceed if no errors are thrown.
        // console.log(typeof exchangedValue);


        // const title = "dothisthing!";
        // const description =  "make this a description";
        // const zipCode = "99999";
        // const exchangedValue = false;
        // const itemId = a1vkV;

        const proceed = true;
        // await this.client.updateListing(title, description, zipCode, exchangedValue, itemId,(error));
        await this.client.updateListing(title, description, zipCode, exchangedValue, itemId, (error) => {
            updateButton.innerText = origButtonText;
            errorMessageDisplay.innerText = `Error: ${error.message}`;
            errorMessageDisplay.classList.remove('hidden');
            proceed = false;
        });


        this.dataStore.set('listing', listing);   
        this.redirectToViewMemberDashboard(proceed);
    }

    /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     */
    redirectToViewMemberDashboard(proceed) {
        if (proceed == true){
            const user = this.authenticator.getCurrentUserInfo;
            if (user != null) {
                window.location.href = `/memberDashboard.html?id=${user.email}`;
            }
        }

    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const updateListing = new UpdateListing();
    updateListing.mount();
};

window.addEventListener('DOMContentLoaded', main);
