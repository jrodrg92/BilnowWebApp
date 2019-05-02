var fs= require('fs');
class ctrlUser{

    constructor(route,dir,user,res,pet){

        this.roure=route;
        this.dir=dir;
        this.user=user;

        console.log(user.nom_Usuario)

        res.render(dir + '/views/user.html', {nom_Usuario:user.nom_Usuario,
                                              ap_Usuario:user.ap_Usuario, 
                                              tlf_Usuario:user.tlf_Usuario, 
                                              dir_Usuario:user.dir_Usuario, 
                                              email_Usuario:user.email_Usuario});     
            
        pet.findAll({model:this.pet, where:{id_}});
                                              
        

    }


}

module.exports = ctrlUser;