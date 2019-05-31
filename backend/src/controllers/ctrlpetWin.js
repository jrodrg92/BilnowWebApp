const ctrlUserWin= require('../controllers/ctrlUser');

class CtrlPetWin{

    constructor(routes,dir,user,res,pet,prod,date,reserve,prodreserved,actuaPet){

        this.route=routes;
        this.dir=dir;
        this.usuario=user;
        this.prod=prod;
        this.reserve=reserve;
        this.prodreserved=prodreserved;
        this.petMod=pet;
        this.date=date;
        this.pet=actuaPet;
        this.datef=[]


        this.date.findAll({model:this.date, where:{id_Mscota:this.pet.dataValues.id_Mascota}}).then(dates => {

          this.datef=dates;

          res.render(dir + '/views/petWin', {dates, id_Mascota:this.pet.dataValues.id_Mascota,
                                             nombre_Mascota:this.pet.dataValues.nombre_Mascota,
                                             esp_Mascota:this.pet.dataValues.esp_Mascota,
                                             raza_Mascota:this.pet.dataValues.raza_Mascota,
                                             capa_Mascota:this.pet.dataValues.capa_Mascota,
                                             fecha_Nacimiento:this.pet.dataValues.fecha_Nacimiento});
        });       

        this.route.post('/borrarCita', (req,res,next) => {
          var dates=this.datef;

          this.date.destroy({where:{ID_Cita:dates[req.query.id].ID_Cita}});

          this.datef.splice(req.query.id,1);

          dates=this.datef;
          
          next( res.render(dir + '/views/petWin', {dates, id_Mascota:this.pet.dataValues.id_Mascota,
                                                          nombre_Mascota:this.pet.dataValues.nombre_Mascota,
                                                          esp_Mascota:this.pet.dataValues.esp_Mascota,
                                                          raza_Mascota:this.pet.dataValues.raza_Mascota,
                                                          capa_Mascota:this.pet.dataValues.capa_Mascota,
                                                          fecha_Nacimiento:this.pet.dataValues.fecha_Nacimiento}));

        });

        this.route.post('/borrarPet', (req,res)=>{

          this.petMod.destroy({where:{id_Mascota:this.pet.dataValues.id_Mascota}});

          this.petMod.findAll({model: this.pet, where:{id_Duenio:user.id_Usuario}}).then(pets=>{
            res.render(dir + '/views/user', {pets,nom_Usuario:user.nom_Usuario,
                                                  ap_Usuario:user.ap_Usuario, 
                                                  tlf_Usuario:user.tlf_Usuario, 
                                                 dir_Usuario:user.dir_Usuario, 
                                                 email_Usuario:user.email_Usuario});

        });

        });

    }

}

module.exports = CtrlPetWin;