const Usermod = require('../models/user');
const petMod = require('../models/pet');
const prodMod =require('../models/product');
const reserve =require('../models/reserve');
const date=require('../models/date');
const prodreserved= require('../models/productsReserved');
const ctrlUserWin= require('../controllers/ctrlUser');
const ctrlAddUser= require('../controllers/ctrlAddUsr');
const conectDB= require('../utils/conectdb');

var conection = conectDB.getConection();

class ctrLogin{

    constructor(route, dir){

        this.user=Usermod.getUser(conection);
        this.pet=petMod.getPet(conection,this.user);
        this.reserve=reserve.getReserve(conection,this.user);
        this.prod=prodMod.getProduct(conection,this.user);
        this.prodreserved =prodreserved.getProductRes(conection,this.reserve);
        this.date=date.getDate(conection,this.pet);

        this.route=route;
        this.dir=dir;

        route.get('/backuser', (req,res)=>{});

        route.get('/',(req,res)=>{

            res.render(dir + '/views/login');
        
        });

        route.post('/user', (req,res) => {
                        
            //Posts.findAll({ include: [{ model: User, where: {year_birth: 1984} }] }).then(posts => { /* ... */ }); 

            var idUser=req.body.id_Usuario;
            var psswd=req.body.pswd_Usuario;

           this.user.findOne({model:this.user, where:{id_Usuario: idUser, pswd_Usuario: psswd}}).then(usuario =>{

                if(usuario==null){
                    console.log("vacio");
                }
                else{

                    const ctrlUser = new ctrlUserWin(this.route, dir, usuario, res, this.pet, this.prod, this.date,  this.reserve, this.prodreserved);

                }

            })
         
        });

        route.get('/addUser', (req,res) => {

            var addUser= new ctrlAddUser(this.route, this.dir, this.User, res, this.prod);

        });

   }
    
}

module.exports = ctrLogin;