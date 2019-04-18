var CtrlLogin = require('./controllers/ctrlLogin');
var express=require('express');
var path=require('path');
const route = express.Router();

module.exports = {
  
    inicio: function(route){

      var ctrlMain=new CtrlLogin(route, __dirname);
    

    }

}


