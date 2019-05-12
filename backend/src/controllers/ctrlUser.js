var fs= require('fs');
const ctrlStor = require('../controllers/ctrlStore');
class ctrlUser{

    constructor(route,dir,user,res,pet,prod){

        this.roure=route;
        this.dir=dir;
        this.user=user;
        this.prod=prod;

        console.log(user.nom_Usuario)

        res.render(dir + '/views/user', {nom_Usuario:user.nom_Usuario,
                                              ap_Usuario:user.ap_Usuario, 
                                              tlf_Usuario:user.tlf_Usuario, 
                                              dir_Usuario:user.dir_Usuario, 
                                              email_Usuario:user.email_Usuario});   
                                              
        route.get('/store', (req,res) => {

            var ctrlstore = new ctrlStor(this.route, this.dir, this.user, res, this.prod, req);

        });
            
                                              
        

    }


}

module.exports = ctrlUser;