const Sequelize = require('sequelize')

module.exports = {

    getProductRes: function(sequelize,reserves,prod){

        const ProductRes=sequelize.define("productosreservados",{
           
            id_Reserva: {type: Sequelize.STRING, primaryKey:true},
            id_Producto: Sequelize.STRING

        });

        ProductRes.hasMany(reserves);
        ProductRes.hasMany(prod);
        
        return ProductRes;

    }

};