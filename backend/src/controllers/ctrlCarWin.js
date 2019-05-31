const ctrlUser = require('../controllers/ctrlUser');
const faker= require('faker');
class CtrlCarWin{
    
    constructor(route, res, dir, user, reserve, prodreserved, products){

        this.dir=dir;
        this.user=user;
        this.res=res;
        this.products=products;
        this.reserve=reserve;
        this.prodreserved=prodreserved;

        this.res.render(dir + '/views/carrito', {products});


        route.post('/borrarProd',(req,res,next)=>{

            const prodsMod=[];

            this.products.splice(req.query.id,1);

            next(res.render(dir + '/views/carrito', {products}));

        });

        route.post('/makeBuy', (req,res,next)=>{

            let idreserve = faker.random.uuid();
            console.log(reserve.length);

            for(var i=0; i<products.length; i++){

                this.prodreserved.create({id_Reserva:faker.random.uuid(), id_Producto:products[i].id_Producto, productosreservadoIdProducto:products[i].id_Producto}).then();


            }

            reserve.create({id_Reserva:idreserve, id_Usuario:user.id_Usuario}).then();


        });

    }

}

module.exports = CtrlCarWin;