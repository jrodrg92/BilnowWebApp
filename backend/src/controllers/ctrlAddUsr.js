class CtrlAddUser{

    constructor(route, dir, User, res){

        this.route=route;
        this.dir=dir;
        this.User=User;

        res.sendFile(dir + '/views/addUser.html');
        
        route.post('/add', (req, res) => {

            User.create(req.body)
                .then(user => res.json(user))

        });
    }

}

module.exports = CtrlAddUser;