var CtrlLogin = require('./controllers/ctrlLogin');
var express=require('express');
var path=require('path');
const route = express.Router();

module.exports = {
  
    main: function(route){

      this.route=route;
      var ctrlMain=new CtrlLogin(this.route, __dirname); 

    }

}



