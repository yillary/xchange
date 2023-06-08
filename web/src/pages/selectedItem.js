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
        this.bindClassMethods(['imageTabs'], this);
        this.dataStore = new DataStore();
//        this.dataStore.addChangeListener(this.addPlaylistToPage);
//        this.dataStore.addChangeListener(this.addSongsToPage);
        this.header = new Header(this.dataStore);
        console.log("SelectedItem constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const itemId = urlParams.get('id');
        document.getElementById('playlist-name').innerText = "Loading Listing ...";
        const item = await this.client.getItem(itemId);
        this.dataStore.set('item', item);
    }

    /**
     * Add the header to the page and load the XchangeClient.
     */
    mount() {
        document.getElementById('add-song').addEventListener('click', this.addSong);

        this.header.addHeaderToPage();

        this.client = new MusicPlaylistClient();
        this.clientLoaded();
    }

//
//function imageTabs(imgs) {
//  // Get the expanded image
//  var expandImg = document.getElementById("expandedImg");
//  // Get the image text
//  var imgText = document.getElementById("imgtext");
//  // Use the same src in the expanded image as the image being clicked on from the grid
//  expandImg.src = imgs.src;
//  // Use the value of the alt attribute of the clickable image as text inside the expanded image
//  imgText.innerHTML = imgs.alt;
//  // Show the container element (hidden with CSS)
//  expandImg.parentElement.style.display = "block";
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const viewPlaylist = new ViewPlaylist();
    viewPlaylist.mount();
};

window.addEventListener('DOMContentLoaded', main);