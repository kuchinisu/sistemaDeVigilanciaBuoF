const { app, BrowserWindow } = require('electron');
const url = require('url');
const path = require('path');

console.log('el pepe')

app.on('ready', () => {
    mainWindow = new BrowserWindow({})
    mainWindow.loadURL(url.format({
        pathname: path.join(__dirname, 'views/index.html'),
        protocol: 'file',
        slashes: true,
    }))
});