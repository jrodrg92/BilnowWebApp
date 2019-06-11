var express = require('express');
var init = require('./src/routes');
var path = require('path');
var bodyParser=require('body-parser');

const app= express(); //Se crea una instancia de la aplicacion para configurarla

// settings
app.set('port', 3000);
app.use('/frontend', express.static(__dirname + '/frontend'));
app.set('views', path.join(__dirname, '/src/backend/views'));
app.engine('html', require('ejs').renderFile);
app.set('view engine', 'ejs');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended:true
}));

app.use(init);

// listening the Server
app.listen(app.get('port'), () => {
  console.log('Server on port', app.get('port'));
});