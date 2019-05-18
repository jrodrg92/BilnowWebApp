var fs= require('fs');
const ctrlStor = require('../controllers/ctrlStore');
const CtrlAddPet = require('../controllers/ctrlAddPet');
class ctrlUser{

    constructor(route,dir,user,res,pet,prod){

        this.dir=dir;
        this.user=user;
        this.prod=prod;
        this.pet=pet;

        this.pet.findAll({model: this.pet, where:{id_Duenio:user.id_Usuario}}).then(pets=>{

            res.render(dir + '/views/user', {pets,nom_Usuario:user.nom_Usuario,
                                                  ap_Usuario:user.ap_Usuario, 
                                                  tlf_Usuario:user.tlf_Usuario, 
                                                 dir_Usuario:user.dir_Usuario, 
                                                 email_Usuario:user.email_Usuario});

        });
           
                                              
        route.get('/store', (req,res) => {

            var ctrlstore = new ctrlStor(route, this.dir, this.user, res, this.prod, req, this.pet);

        });

        route.get('/addPet', (req,res) => {
            var addPet= new CtrlAddPet(route, this.dir, this.user, res, this.pet, this.prod);
        });
            
        route.post('/showInfoGet', (req,res) => {



        });                                 
        

    }


}

module.exports = ctrlUser;