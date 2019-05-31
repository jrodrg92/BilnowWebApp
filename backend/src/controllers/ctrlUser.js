const ctrlsPetWin =require('../controllers/ctrlpetWin');
const ctrlStor = require('../controllers/ctrlStore');
const CtrlAddPet = require('../controllers/ctrlAddPet');
class ctrlUser{

    constructor(route,dir,user,res,pet,prod,date,reserve,prodreserved){

        this.route=route;
        this.dir=dir;
        this.user=user;
        this.prod=prod;
        this.pet=pet;
        this.date=date;
        this.reserve=reserve;
        this.prodreserved=prodreserved;
        this.petsO =[];

        this.pet.findAll({model: this.pet, where:{id_Duenio:user.id_Usuario}}).then(pets=>{
            this.petsO=pets;
            res.render(dir + '/views/user', {pets,nom_Usuario:user.nom_Usuario,
                                                  ap_Usuario:user.ap_Usuario, 
                                                  tlf_Usuario:user.tlf_Usuario, 
                                                 dir_Usuario:user.dir_Usuario, 
                                                 email_Usuario:user.email_Usuario});

        });
           
                                              
        route.get('/store', (req,res) => {

            var ctrlstore = new ctrlStor(this.route, this.dir, this.user, res, this.prod, req, this.pet, this.prodreserved, this.reserve);

        });

        route.get('/addPet', (req,res, next) => {
            var addPet= new CtrlAddPet(this.route, this.dir, this.user, res, this.pet, this.prod, this.date, this.reserve, this.prodreserved);
        });
            
        route.post('/showPetInfo', (req,res) => {

                var ctrlpetwin =new ctrlsPetWin(this.route, this.dir, this.user, res, this.pet, this.prod, this.date,  this.reserve, this.prodreserved, this.petsO[req.query.id]);
    

        });                                 
        

    }


}

module.exports = ctrlUser;