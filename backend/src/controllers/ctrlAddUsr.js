const ctrlUser = require('../controllers/ctrlUser');
class CtrlAddUser{

    constructor(route, dir, User, res, prod){

        this.route=route;
        this.dir=dir;
        this.User=User;
        this.Prod=prod;

        res.render(dir + '/views/addUser');
        
        route.post('/add', (req, res) => {

            User.create(req.body)
                .then(user);

            var winUser=new ctrlUser(route, dir, user, res, Prod);

        });
    }

}

module.exports = CtrlAddUser;