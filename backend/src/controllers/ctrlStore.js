class CtrlStore{

    constructor(route, dir, user, res, prod, req){

        this.route=route;
        this.dir=dir;
        this.User=user;
        
        let perPage = 9;
        let page=req.params.page || 1;

        prod.findAll({ include: [{atributes: ['nom_Producto','foto_Producto','fabricante_Pro','price_Producto']}] }).then(prods => { 
            console.log(prods.dataValues);
         });

    }

}

module.exports = CtrlStore;