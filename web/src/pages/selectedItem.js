import XchangeClient from '../api/xchangeClient';
import Header from '../components/header';
import BindingClass from "../util/bindingClass";
import DataStore from "../util/DataStore";

/**
 * Logic needed for the view playlist page of the website.
 */
class SelectedItem extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
        console.log("selectedItem constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const itemId = urlParams.get('itemId');
        //document.getElementById('item-title').innerText = "Loading Playlist ...";
        const item = await this.client.getItem(itemId);
        this.dataStore.set('item', item);
        document.getElementById('item-title').innerText = item.title;
        document.getElementById('item-description').innerText = item.description;

             console.log("phase one " + item);
              const emailButton = document.getElementById('email-button');

                emailButton.href = 'mailto:' + item.email;
                console.log("item.email is: " + item.email);


    }

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

        this.client = new XchangeClient();
        this.clientLoaded();

    }

//    addEmailButton() {
////      const item = this.dataStore.get('item');
////     console.log("phase one " + item);
////      const emailButton = document.getElementById('email-button');
////      emailButton.addEventListener('click', function() {
////        emailButton.href = 'mailto:' + item.email;
////        console.log("item.email is: " + item.email);
////      });
//    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const selectedItem = new SelectedItem();
    selectedItem.mount();
};

window.addEventListener('DOMContentLoaded', main);