const ctrlUser = require('../controllers/ctrlUser');
class CtrlAddPet{

    constructor(route, dir, user, res, pet, Prod){

        this.route=route;
        this.dir=dir;
        this.User=user;
        this.Prod=Prod;

        res.render(dir + '/views/addPet');
        
        route.post('/add', (req, res) => {

            pet.create(req.body)
                .then(pet =>{
                    const winUser=new ctrlUser(route,this.dir,this.User,res,pet,this.Prod);
                }
                );

        });
    }

}

module.exports = CtrlAddPet;