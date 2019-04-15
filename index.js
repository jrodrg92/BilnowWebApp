var express = require('express');
var init = require('./backend/src/routes');
var bodyParser=require('body-parser');

const app= express();

// settings
app.set('port', 3000);
app.use('/frontend', express.static(__dirname + '/frontend'));
app.set('view engine', 'html');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
  extended:true
}));

init.main(app);

// listening the Server
app.listen(app.get('port'), () => {
  console.log('Server on port', app.get('port'));
});
