//import Header from '../components/header';
//import BindingClass from "../util/bindingClass";
//import DataStore from "../util/DataStore";
//
//const MEMBER_LISTINGS_KEY = 'member-listings';
//const EMPTY_DATASTORE_STATE = {
//  [MEMBER_LISTINGS_KEY]: [],
//};
//
//class MemberListingsFetcher extends BindingClass {
//  constructor(xchangeClient, cognitoEmail) {
//    super();
//    this.xchangeClient = xchangeClient;
//    this.cognitoEmail = cognitoEmail;
//
//    this.bindClassMethods(['fetchMemberListings', 'displayMemberListings'], this);
//
//    this.dataStore = new DataStore(EMPTY_DATASTORE_STATE);
//    this.header = new Header(this.dataStore);
//    this.dataStore.addChangeListener(this.displayMemberListings);
//    this.table - new Table();
//  }
//
//  async fetchMemberListings() {
//    try {
//      const listings = await this.xchangeClient.getMemberListings(`/members/${encodeURIComponent(this.cognitoEmail)}`);
//      this.dataStore.setState({ [MEMBER_LISTINGS_KEY]: listings });
//    } catch (error) {
//      console.error('Error fetching member listings:', error);
//      //TODO:check if member is in table. if not, get started button to create a new member.
//    }
//  }
//
//  displayMemberListings() {
//    const memberListings = this.dataStore.get(MEMBER_LISTINGS_KEY);
//      this.table.addTableToPage();
//      this.table.buildTable(memberListings);
//  }
//
//  mount() {
//    this.fetchMemberListings();
//  }
//}
//
//// Usage:
//
///**
// * Main method to run when the page contents have loaded.
// */
//const main = async () => {
//    const memberListingsFetcher = new MemberListingsFetcher();
//    memberListings.mount();
//};
//
//window.addEventListener('DOMContentLoaded', main);
//
//const xchangeClient = new XchangeClient();
//const cognitoEmail = getCognitoEmail(); // Get the Cognito email variable
//
//const memberListingsFetcher = new MemberListingsFetcher(xchangeClient, cognitoEmail);
//memberListingsFetcher.mount();