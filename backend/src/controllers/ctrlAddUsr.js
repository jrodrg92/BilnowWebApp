const ctrlUser = require('../controllers/ctrlUser');
class CtrlAddUser{

    constructor(route, dir, User, res){

        this.route=route;
        this.dir=dir;
        this.User=User;

        res.render(dir + '/views/addUser.html');
        
        route.post('/add', (req, res) => {

            User.create(req.body)
                .then(user);

            var winUser=new ctrlUser(route, dir, user, res);

        });
    }

}

module.exports = CtrlAddUser;