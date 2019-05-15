const Sequelize = require('sequelize')

module.exports = {

    getReserve: function(sequelize,user){

        const Reserves=sequelize.define("reserva",{
           
            id_Reserva: {type: Sequelize.STRING, primaryKey:true},
            id_Usuario: Sequelize.STRING

        });
        
        user.hasMany(Reserves);

        return Reserves;

    }

};