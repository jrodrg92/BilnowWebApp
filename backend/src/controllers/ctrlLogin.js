const User = require('../models/user');
const conectDB= require('../utils/conectdb');

var conection = conectDB.getConection();
class ctrLogin{

    constructor(route, dir){

        this.route=route;
        this.dir=dir;
        route.get('/',(req,res)=>{

            res.sendFile(dir + '/views/login.html');
        
        });

        route.post('/login', (req,res) => {

            req.body.id_Usuario
            
            var user=User.getUser(conection);
            
            //Posts.findAll({ include: [{ model: User, where: {year_birth: 1984} }] }).then(posts => { /* ... */ }); 

            var idUser=req.body.id_Usuario;

            user.findAll({where: {id_Usuario: idUser} }).then(usuario => {             
                usuario.forEach(elemento => {

                console.log(elemento.dataValues);
                
              })
            })
            .catch(err=>{
              console.log(err);
            })

        });

        route.get('/addUser', (req,res) => {

            res.sendFile(dir+"/views/addUser.html");

        });

   }
    
}

module.exports = ctrLogin;