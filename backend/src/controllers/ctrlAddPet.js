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

            pet.create(req.body);

            res.redirect('/user');

        });
    }

}

module.exports = CtrlAddPet;