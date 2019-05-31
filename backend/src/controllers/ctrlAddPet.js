const ctrlUser = require('../controllers/ctrlUser');
class CtrlAddPet{

    constructor(route, dir, user, res, pet, Prod, date,  reserve, prodreserved){

        this.route=route;
        this.dir=dir;
        this.user=user;
        this.Prod=Prod;
        this.date=date;
        this.reserve=reserve;
        this.prodreserved=prodreserved;

        res.render(dir + '/views/addPet');
        
        route.post('/addPet', (req, res, next) => {

            pet.create({ id_Mascota:req.body.id_Mascota,
                nombre_Mascota:req.body.nombre_Mascota,
                esp_Mascota:req.body.esp_Mascota,
                raza_Mascota:req.body.raza_Mascota,
                capa_Mascota:req.body.capa_Mascota,
                fecha_Nacimiento:new Date(req.body.datepicker),
                sexo_Mascota:req.body.sexo_Mascota,
                id_Duenio:user.id_Usuario});


                pet.findAll({model: this.pet, where:{id_Duenio:user.id_Usuario}}).then(pets=>{
                    this.petsO=pets;
                    res.render(dir + '/views/user', {pets,nom_Usuario:user.nom_Usuario,
                                                          ap_Usuario:user.ap_Usuario, 
                                                          tlf_Usuario:user.tlf_Usuario, 
                                                         dir_Usuario:user.dir_Usuario, 
                                                         email_Usuario:user.email_Usuario});
        
                });

        });
    }

}

module.exports = CtrlAddPet;