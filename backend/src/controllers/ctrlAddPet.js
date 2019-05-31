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
        
        route.post('/addPet', (req, res) => {

            

            console.log(req.body.id_Mascota);
            console.log(req.body.nombre_Mascota);
            console.log(req.body.esp_Mascota);
            console.log(req.body.raza_Mascota);
            console.log(req.body.capa_Mascota);
            console.log(req.body.datepicker);
            //revisar sexo mascota
            console.log(req.body.sexo_Mascota);

        });
    }

}

module.exports = CtrlAddPet;