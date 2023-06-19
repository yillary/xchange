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
const SEARCH_ZIP_CODE_KEY = 'search-zip-code';
const SELECTED_TYPE_KEY = 'selected-type'; // Add a new constant for the selected type

const EMPTY_DATASTORE_STATE = {
  [SEARCH_CRITERIA_KEY]: '',
  [SEARCH_RESULTS_KEY]: [],
  [SEARCH_ZIP_CODE_KEY]: '',
  [SELECTED_TYPE_KEY]: '', 
};


/**
 * Logic needed for the view playlist page of the website.
 */
class SearchListings extends BindingClass {
    constructor() {
        super();

        this.bindClassMethods(['mount', 'search', 'displaySearchResults', 'getHTMLForSearchResults'], this);

        // Create a new datastore with an initial "empty" state.
        this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
        this.header = new Header(this.dataStore);
        this.dataStore.addChangeListener(this.displaySearchResults);
    }

    /**
     * Add the header to the page and load the XchangeClient.
     */
    mount() {
        // Wire up the form's 'submit' event and the button's 'click' event to the search method.
        document.getElementById('search-listings-form').addEventListener('submit', this.search.bind(this));
        document.getElementById('search-btn').addEventListener('click', this.search.bind(this));

        this.header.addHeaderToPage();
        this.client = new XchangeClient();
    }


    /**
     * Uses the client to perform the search,
     * then updates the datastore with the criteria and results.
     * @param evt The "event" object representing the user-initiated event that triggered this method.
     */
    async search(evt) {
        evt.preventDefault();

        const searchCriteria = document.getElementById('search-criteria').value;
        const searchZipCode = document.getElementById('search-zip-code').value;
        const selectedType = document.querySelector('input[name="type_selector"]:checked');

        // if (!selectedType) {
        //     // Radio button not selected, handle the error or show a message to the user
        //     // For now, let's just return without performing the search
        //     return;
        // }

        const selectedTypeValue = selectedType.value; // Retrieve the value of the selected type
        const previousSearchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const previousSearchZipCode = this.dataStore.get(SEARCH_ZIP_CODE_KEY);
        const previousSelectedType = this.dataStore.get(SELECTED_TYPE_KEY);

        if (
            previousSearchCriteria === searchCriteria &&
            previousSearchZipCode === searchZipCode &&
            previousSelectedType === selectedTypeValue ||
            previousSearchZipCode === searchZipCode &&
            previousSelectedType === selectedTypeValue
        ) {
            return;
        }

        if (searchCriteria && searchZipCode && selectedTypeValue || searchZipCode && selectedTypeValue) {
            const results = await this.client.search(searchCriteria, searchZipCode, selectedTypeValue);
            console.log("SearchListings coming back from client results: " +  results);
            this.dataStore.setState({
                [SEARCH_CRITERIA_KEY]: searchCriteria,
                [SEARCH_RESULTS_KEY]: results,
            });
            // Handle the response accordingly
        } else {
            this.dataStore.setState(EMPTY_DATASTORE_STATE);
        }
    }

    /**
     * Pulls search results from the datastore and displays them on the html page in the image gallery.
     */
    displaySearchResults() {
        const searchCriteria = this.dataStore.get(SEARCH_CRITERIA_KEY);
        const searchResults = this.dataStore.get(SEARCH_RESULTS_KEY);

        const searchResultsContainer = document.getElementById('search-results-container');
        const searchCriteriaDisplay = document.getElementById('search-criteria-display');
        const searchResultsDisplay = document.getElementById('search-results-display');


            searchResultsContainer.classList.remove('hidden');
            searchCriteriaDisplay.innerHTML = `"${searchCriteria}"`;
            searchResultsDisplay.innerHTML = '';

            for (const item of searchResults) {
                const galleryItem = document.createElement('div');
                galleryItem.className = 'responsive';

                const galleryContent = document.createElement('div');
                galleryContent.className = 'gallery';

                const itemLink = document.createElement('a');
                itemLink.id = document.createElement('id');
                itemLink.href = '/selectedItem.html?itemId=' + item.itemId;

//                const itemImage = document.createElement('img');
//                itemImage.src = item.imageUrl;
//                itemImage.alt = item.title;
//                itemImage.width = 600;
//                itemImage.height = 400;

//Make a lisf of pic urls by category. If criteria is x then provide locally stored list and insert pic.

                const itemTitle = document.createElement('div');
                itemTitle.className = 'desc';
                itemTitle.textContent = item.title;

                itemLink.appendChild(itemTitle);
                galleryContent.appendChild(itemLink);
                galleryItem.appendChild(galleryContent);
                searchResultsDisplay.appendChild(galleryItem);
        }
    }

    /**
     * Create appropriate HTML for displaying searchResults on the page.
     * @param searchResults An array of playlists objects to be displayed on the page.
     * @returns A string of HTML suitable for being dropped on the page.
     */
    getHTMLForSearchResults(searchResults) {
        if (searchResults.length === 0) {
            return '<h4>No results found</h4>';
        }

        let html = '<tr><th>Title</th><th>Description</th></tr>';
        for (const res of searchResults) {
            html += `
            <tr>
                <td>
                    <a href="item.html?itemId=${res.itemId}">${res.title}</a>
                </td>
                <td>${res.description}</td>
//                <td>${res.tags?.join(', ')}</td>
            </tr>`;
        }
//        html += '</table>';

        return html;
    }

}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const searchListings = new SearchListings();
    searchListings.mount();
};

window.addEventListener('DOMContentLoaded', main);