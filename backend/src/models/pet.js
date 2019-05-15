    const Sequelize = require('sequelize');

module.exports = {

    getPet : function(sequelize,user){

        const Pet= sequelize.define("mascotas",{

            id_Mascota:{type: Sequelize.STRING,primaryKey:true},
            nombre_Mascota:Sequelize.STRING,
            esp_Mascota:Sequelize.STRING,
            raza_Mascota:Sequelize.STRING,
            capa_Mascota:Sequelize.STRING,
            fecha_Nacimiento:Sequelize.DATE,
            sexo_Mascota:Sequelize.INTEGER,
            id_Duenio:Sequelize.STRING

        },{

            freezeTableNane: true

        });

        user.hasMany(Pet);

        return Pet;

    }

}