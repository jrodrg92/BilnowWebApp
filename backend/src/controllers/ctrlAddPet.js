const ctrlUser = require('../controllers/ctrlUser');
class CtrlAddPet{

    constructor(route, dir, user, res, pet, Prod, date,  reserve, prodreserved){

        this.route=route;
        this.dir=dir;
        this.User=user;
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

                next(window.history.back());

        });
    }

}

module.exports = CtrlAddPet;