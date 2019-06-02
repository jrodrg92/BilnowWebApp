var express = require('express');
var init = require('./src/routes');
var bodyParser=require('body-parser');

const app= express();

// settings
app.set('port', 3000);
app.use('/views', express.static(__dirname + '/src/backend/views'));
app.use('/frontend', express.static(__dirname + '/frontend'));
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