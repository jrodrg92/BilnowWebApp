var express=require('express');
var path=require('path');
const route = express.Router();

module.exports = {
  
    main: function(route){

      route.get('/',(req,res)=>{

        res.sendFile(__dirname + '/views/login.html');
    
        const User = require('./models/user');
        const conectDB= require('./utils/conectdb');
        
        var conection = conectDB.getConection();
        
        var user=User.getUser(conection);
        
        user.findAll({attributes:['id_Usuario']}).then(usuario => {
          usuario.forEach(elemento => {
            console.log(elemento.dataValues);
          })
        })
        .catch(err=>{
          console.log(err);
        })
    
    });
    

    }

}


