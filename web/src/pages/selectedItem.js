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
        this.bindClassMethods(['clientLoaded'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.addPlaylistToPage);
        this.dataStore.addChangeListener(this.addSongsToPage);
        this.header = new Header(this.dataStore);
        console.log("selectedItem constructor");
    }

    /**
     * Once the client is loaded, get the playlist metadata and song list.
     */
    async clientLoaded() {
        const urlParams = new URLSearchParams(window.location.search);
        const itemId = urlParams.get('itemId');
        document.getElementById('item-title').innerText = "Loading Playlist ...";
        const item = await this.client.getItem(itemId);
        this.dataStore.set('item', item);
        document.getElementById('item-title').innerText = item.title;
        document.getElementById('item-description').innerText = item.description;
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     * MY NOTES: I think this one is reloading the page when someone wants to add a song. It's mean for an update
     * to save the new state of the item. I don't think I need it.
     */
    mount() {
//        document.getElementById('add-song').addEventListener('click', this.addSong);

        this.header.addHeaderToPage();

        this.client = new XchangeClient();
        this.clientLoaded();
    }


//// I DON' THINK I NEED ANY OF THIS CUZ IT'S JUST UPDATING THE STATE OF OBJECTS. ALL I NEED IS TO DISPLAY.
//    /**
//     * When the playlist is updated in the datastore, update the playlist metadata on the page.
//     */
//    addPlaylistToPage() {
//        const playlist = this.dataStore.get('playlist');
//        if (playlist == null) {
//            return;
//        }
//
//        document.getElementById('playlist-name').innerText = playlist.name;
//        document.getElementById('playlist-owner').innerText = playlist.customerName;
//
//        let tagHtml = '';
//        let tag;
//        for (tag of playlist.tags) {
//            tagHtml += '<div class="tag">' + tag + '</div>';
//        }
//        document.getElementById('tags').innerHTML = tagHtml;
//    }
//
//    /**
//     * When the songs are updated in the datastore, update the list of songs on the page.
//     */
//    addSongsToPage() {
//        const songs = this.dataStore.get('songs')
//
//        if (songs == null) {
//            return;
//        }
//
//        let songHtml = '';
//        let song;
//        for (song of songs) {
//            songHtml += `
//                <li class="song">
//                    <span class="title">${song.title}</span>
//                    <span class="album">${song.album}</span>
//                </li>
//            `;
//        }
//        document.getElementById('songs').innerHTML = songHtml;
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