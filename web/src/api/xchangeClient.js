import axios from "axios";
import BindingClass from "../util/bindingClass";
import Authenticator from "./authenticator";

/**
 * Client to call the XchangeService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class XchangeClient extends BindingClass {

    constructor(props = {}) {
        super();

        const methodsToBind = ['clientLoaded', 'getIdentity', 'login', 'logout', 'getItem', 'search', 'getMemberListings'];
        this.bindClassMethods(methodsToBind, this);

        this.authenticator = new Authenticator();;
        this.props = props;

        axios.defaults.baseURL = process.env.API_BASE_URL;
        this.axiosClient = axios;
        this.clientLoaded();
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     */
    clientLoaded() {
        if (this.props.hasOwnProperty("onReady")) {
            this.props.onReady(this);
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async login() {
        this.authenticator.login();
    }

    async logout() {
        this.authenticator.logout();
    }

    async getTokenOrThrow(unauthenticatedErrorMessage) {
        const isLoggedIn = await this.authenticator.isUserLoggedIn();
        if (!isLoggedIn) {
            throw new Error(unauthenticatedErrorMessage);
        }

        return await this.authenticator.getUserToken();
    }

    /**
     * Gets the item for the given ID.
     * @param id Unique identifier for an item
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The item's metadata.
     */
    async getItem(itemId, errorCallback) {
        try {
            const response = await this.axiosClient.get(`items/${itemId}`);
            console.log(response);
            return response.data.item;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Search for an item.
     * @param criteria A string containing search criteria to pass to the API.
     * @returns The items that match the search criteria.
     */
    async search(criteria, zipCode, type, errorCallback) {
        try {
            const queryParams = new URLSearchParams()
            queryParams.append('q', criteria);
            queryParams.append('zipCode', zipCode);
            queryParams.append('type', type);

            const queryString = queryParams.toString();
            console.log("queryString: " + queryString);

            const response = await this.axiosClient.get(`items/search?${queryString}`);


            return response.data.items;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    /**
     * Gets the member's listings for the given email of member.
     * @param id Unique identifier for an item
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The json of listings associated with member.
     */
//    async getMemberListings(errorCallback) {
//        try {
//            const token = await this.getTokenOrThrow("You must be logged in to view items.");
//            console.log("response in getMemberListings", response);
//            return response.data;
////            return response.data.listings;
//        } catch (error) {
//            this.handleError(error, errorCallback)
//        }
//    }



async getMemberListings(errorCallBack) {
        try {
            const token = await this.getTokenOrThrow("You must be logged in to view items.");
            const response = await this.axiosClient.get(`members`, {
                headers: {
                    Authorization: `Bearer ${token}`
                }
            });
            return response.data;
        } catch (error) {
            this.handleError(error, errorCallBack);
        }
    }

///**
//     * Retrieves all raid objects from the database asynchronously.
//     * @param errorCallback(Optional) A function to execute if the call fails.
//     * @returns All raid objects from the database.
//     */
//    async getAllRaids(errorCallback) {
//        try {
//            const response = await this.axiosClient.get(`raidevents`);
//            console.log("response in get all Raids", response);
//            return response.data;
//        } catch (error) {
//            this.handleError(error, errorCallback);
//        }
//    }


    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The user information for the current user.
     */
    async getIdentity(errorCallback) {
        try {
            const isLoggedIn = await this.authenticator.isUserLoggedIn();

            if (!isLoggedIn) {
                return undefined;
            }

            return await this.authenticator.getCurrentUserInfo();
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }


    /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
    handleError(error, errorCallback) {
      console.error(error);

      const errorFromApi = error?.response?.data?.error_message;
      if (errorFromApi) {
        console.error(errorFromApi);
        error.message = errorFromApi;
      }

      if (typeof errorCallback === 'function') {
        errorCallback(error);
      }
    }

}
