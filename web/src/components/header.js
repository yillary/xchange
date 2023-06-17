import XchangeClient from '../api/xchangeClient';
import BindingClass from "../util/bindingClass";

/**
 * The header component for the website.
 */
export default class Header extends BindingClass {
    constructor() {
        super();

        const methodsToBind = [
            'addHeaderToPage', 'createSiteTitle', 'createUserInfoForHeader',
            'createLoginButton', 'createLoginButton', 'createLogoutButton'
        ];
        this.bindClassMethods(methodsToBind, this);

        this.client = new XchangeClient();
    }

    /**
     * Add the header to the page.
     */
    async addHeaderToPage() {
        const currentUser = await this.client.getIdentity();

        const siteTitle = this.createSiteTitle();
        const userInfo = this.createUserInfoForHeader(currentUser);

        const header = document.getElementById('header');
        header.appendChild(siteTitle);
        header.appendChild(userInfo);
    }

    createSiteTitle() {
        const homeButton = document.createElement('a');
        homeButton.classList.add('header_home');
        homeButton.href = 'index.html';
        homeButton.innerText = 'XCHANGE';

        // const img = document.createElement('img');
        // img.src = 'EXCHANGE-wordmark-smaller.png';
        // img.alt = 'Playlist';
        // img.style.width = '150px';
        // img.style.height = '150px';
        // homeButton.appendChild(img);

        const siteTitle = document.createElement('div');
        siteTitle.classList.add('site-title');
        siteTitle.appendChild(homeButton);

        return siteTitle;
    }


    // createSiteTitle() {
    //     const homeButton = document.createElement('a');
    //     homeButton.classList.add('header_home');
    //     homeButton.href = 'index.html';
    
    //     const img = document.createElement('img');
    //     img.src = 'sixpacklogo.png';
    //     img.alt = 'Playlist';
    //     img.style.width = '150px';
    //     img.style.height = '150px';
    //     homeButton.appendChild(img);
    
    //     const siteTitle = document.createElement('div');
    //     siteTitle.classList.add('site-title');
    //     siteTitle.appendChild(homeButton);
    
    //     return siteTitle;
    // }

 createUserInfoForHeader(currentUser) {
     const userInfo = document.createElement('div');
     userInfo.classList.add('user');

     if (currentUser) {
         const dashboardButton = document.createElement('a');
         dashboardButton.classList.add('button');
         dashboardButton.href = 'memberDashboard.html';
         dashboardButton.innerText = 'MY STUFF';

         const logoutButton = this.createLogoutButton(currentUser);

         userInfo.appendChild(dashboardButton);
         userInfo.appendChild(document.createTextNode('\u00A0')); // add a space between the buttons
         userInfo.appendChild(logoutButton);

         const style = window.getComputedStyle(logoutButton);
         const leftOffset = parseInt(style.getPropertyValue('left'), 10);
         const buttonWidth = parseInt(style.getPropertyValue('width'), 10);

         dashboardButton.style.left = `${leftOffset - buttonWidth - 80}px`; // adjust the left position of the Dashboard button
         userInfo.style.width = `${leftOffset + buttonWidth + 80}px`; // adjust the width of the userInfo container to include the gap and buttons
     } else {
         const loginButton = this.createLoginButton();
         userInfo.appendChild(loginButton);
     }

     return userInfo;
 }


    createLoginButton() {
        return this.createButton('LOGIN', this.client.login);
    }

    createLogoutButton(currentUser) {
        console.log("createLogoutButton");
        return this.createButton(`LOGOUT: ${currentUser.name}`, this.client.logout);
    }

    createButton(text, clickHandler) {
        const button = document.createElement('a');
        button.classList.add('button');
        button.href = '#';
        button.innerText = text;

        button.addEventListener('click', async () => {
            await clickHandler();
        });

        return button;
    }
}
