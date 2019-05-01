const Usermod = require('../models/user');
const ctrlUserWin= require('../controllers/ctrlUser');
const ctrlAddUser= require('../controllers/ctrlAddUsr');
const conectDB= require('../utils/conectdb');

var conection = conectDB.getConection();

class ctrLogin{

    constructor(route, dir){

        this.User=Usermod.getUser(conection);

        this.route=route;
        this.dir=dir;

        route.get('/',(req,res)=>{

            res.sendFile(dir + '/views/login.html');
        
        });

        route.post('/login', (req,res) => {
                        
            //Posts.findAll({ include: [{ model: User, where: {year_birth: 1984} }] }).then(posts => { /* ... */ }); 

            var idUser=req.body.id_Usuario;
            var psswd=req.body.pswd_Usuario;

           User.findOne({model:user, where:{id_Usuario: idUser, pswd_Usuario: psswd}}).then(usuario =>{

                if(usuario==null){
                    console.log("vacio");
                }
                else{

                    //const ctrlUser = new ctrlUserWin(route, dir, conection, user);

                }

            })
         
        });

        route.get('/addUser', (req,res) => {

            var addUser= new ctrlAddUser(this.route, this.dir, this.User, res);

        });

   }
    
}

module.exports = ctrLogin;