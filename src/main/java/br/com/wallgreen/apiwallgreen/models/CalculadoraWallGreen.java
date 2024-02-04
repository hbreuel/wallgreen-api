package br.com.wallgreen.apiwallgreen.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculadoraWallGreen {


    private double larguraModulo = 0.653;
    private double alturaModulo = 0.218;
    private double larguraNicho = 0.2177;
    private double larguraDuplo = 0.4353;
    private double larguraCantoneira = 0.22;
    private boolean temCantoneira;
    private boolean ambosLados;


    private double alturaParede;
    private double larguraParede;
    private double alturaJardimVertical;
    private double larguraJardimVertical;
    private double areaJardimVertical;
    private int totalPlantas;
    private double modulosDeAltura;
    private double modulosDeLargura;
    private double totalModulosInteiros;
    private double totalModulosNicho;
    private double totalModulosDuplos;
    private double totalModulosCantoneira;


    //Estrutural
    private int totalPlacaHorizontal;
    private int totalPlacaVertical;
    private int totalPlacaNicho;
    private int totalPlacaDuplo;
    private int totalVasos;
    private int totalBolsasSubstrato;
    private int totalInicialFinal;
    private int totalPinoDeTravamento;
    private int totalPlacaCantoneira;
    private int totalUniaoCantoneira;
    private int totalVasoCantoneira;
    private int totalBolsaSubstratoCantoneira;
    private int totalPlacaVerticalCantoneira;

    //Irrigação
    private double totalTuboCego;
    private int totalGotejador;
    private int totalConectorTe;
    private int totalFinalDeLinha;
    private int totalConectorCotovelo;
    private int totalConectorInicial;



    public CalculadoraWallGreen(RequestDadosDTO dados) {
        this.larguraParede = dados.larguraParede();
        this.alturaParede = dados.alturaParede();
        this.temCantoneira = dados.temCantoneira();
        this.ambosLados = dados.ambosLados();
    }

    public void calculaDimensoes() {

        if (this.temCantoneira == true && this.ambosLados != true) {

            this.larguraParede -= 0.22;
        }

        if (this.temCantoneira == true && this.ambosLados == true) {

                this.larguraParede -= 0.44;

        }

        this.modulosDeAltura = Math.floor(this.alturaParede / this.alturaModulo);
        this.modulosDeLargura =  Math.floor(this.larguraParede / this.larguraModulo);

        this.totalModulosInteiros = this.modulosDeAltura * this.modulosDeLargura;

        this.alturaJardimVertical = this.modulosDeAltura * alturaModulo + 0.02;
        this.larguraJardimVertical = this.modulosDeLargura * larguraModulo + 0.02;

        if ((this.larguraParede - this.larguraJardimVertical) >= this.larguraNicho && (this.larguraParede - this.larguraJardimVertical) < this.larguraDuplo) {
            this.larguraJardimVertical += this.larguraNicho;
            this.totalModulosNicho = this.modulosDeAltura;
        }

        System.out.println(this.larguraParede - this.larguraJardimVertical);

        if ((this.larguraParede - this.larguraJardimVertical) >= this.larguraDuplo && (this.larguraParede - this.larguraJardimVertical) <= larguraModulo) {
            this.larguraJardimVertical += this.larguraDuplo;
            this.totalModulosDuplos = this.modulosDeAltura;
        }


        if (this.temCantoneira == true && this.ambosLados != true) {

            this.larguraJardimVertical += 0.22;
            this.totalModulosCantoneira = this.modulosDeAltura;
            this.larguraParede += 0.22;
        }

        if (this.temCantoneira == true && this.ambosLados == true) {

            this.larguraJardimVertical += 0.44;
            this.totalModulosCantoneira = this.modulosDeAltura * 2;
            this.larguraParede += 0.44;
        }

        this.areaJardimVertical = this.alturaJardimVertical * this.larguraJardimVertical;


    }


    public void calculaQuantidades() {

        this.totalPlantas = (int) ((this.totalModulosInteiros * 3) + (this.totalModulosDuplos * 2) + (this.totalModulosNicho) + this.totalModulosCantoneira);

        this.totalPlacaHorizontal = (int) ((this.totalModulosInteiros * 4) + (this.modulosDeLargura * 2) );
        this.totalPlacaVertical = (int) ((this.totalModulosInteiros * 3) + this.modulosDeAltura + (this.totalModulosDuplos * 2) + (this.totalModulosNicho));
        this.totalVasos = (int) ((this.totalModulosInteiros * 3) + (this.totalModulosDuplos * 2) + this.totalModulosNicho);
        this.totalBolsasSubstrato = this.totalVasos;
        this.totalInicialFinal = (int) (this.modulosDeAltura * 4);
        this.totalPinoDeTravamento = (int) ((this.totalModulosInteiros * 12) + (this.modulosDeLargura * 12) + (this.modulosDeAltura * 4));
        this.totalPlacaNicho = (int) ((this.totalModulosNicho * 2) + 1);
        this.totalPlacaDuplo = (int) ((this.totalModulosDuplos * 2) + 1);
        this.totalPlacaCantoneira = (int) (this.totalModulosCantoneira + 1);
        this.totalUniaoCantoneira = (int) (this.totalModulosCantoneira);
        this.totalVasoCantoneira = (int) (this.totalModulosCantoneira);
        this.totalBolsaSubstratoCantoneira = this.totalVasoCantoneira;
        this.totalPlacaVerticalCantoneira = (int) (this.totalModulosCantoneira * 2);


        this.totalTuboCego = ((this.modulosDeLargura * larguraModulo) * this.modulosDeAltura) + (this.modulosDeLargura * alturaModulo * 2) + 1;
        this.totalConectorCotovelo = 3;
        this.totalGotejador = this.totalPlantas;
        this.totalFinalDeLinha = (int) (this.modulosDeAltura - 1);
        this.totalConectorTe = (int) (this.modulosDeAltura - 2);







    }







}