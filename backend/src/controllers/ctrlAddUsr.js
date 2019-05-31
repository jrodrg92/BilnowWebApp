const ctrlUserWin = require('../controllers/ctrlUser');
class CtrlAddUser{

    constructor(route,dir,User,res,pet,prod,date,reserve,prodreserved){

        this.route=route;
        this.dir=dir;
        this.prod=prod;
        this.pet=pet;
        this.date=date;
        this.reserve=reserve;
        this.prodreserved=prodreserved;
        this.User=User;

        res.render(dir + '/views/addUser');
        
        route.post('/add', (req, res) => {

            this.User.create(req.body)
                .then(user=>{
                    console.log(user.id_Usuario);
                    const ctrlUser = new ctrlUserWin(this.route, dir, user, res, this.pet, this.prod, this.date,  this.reserve, this.prodreserved);
                });


        });
    }

}

module.exports = CtrlAddUser;