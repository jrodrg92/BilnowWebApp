class ctrlUser{

    constructor(route,dir,usuario){

        this.roure=route;
        this.dir=dir;

        res.sendFile(dir + '/views/user.html');

    }


}

module.exports = ctrlUser;