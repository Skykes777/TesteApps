package com.alejandro.calculadoradeimc.calculo

import java.text.DecimalFormat

class CalcularIMC {
    fun calculoIMC(peso: String, altura: String): String {
        val pesoConvertido = peso.toDouble()
        val alturaConvertida = altura.toDouble()
        val imc = pesoConvertido / (alturaConvertida * alturaConvertida)
        val formatoDecimal = DecimalFormat("0.00")
        val resposta: String
        if (imc < 18) {
            resposta = "Peso Baixo\nIMC: ${formatoDecimal.format(imc)}"
        } else if (imc <= 24) {
            resposta = "Peso Normal\nIMC: ${formatoDecimal.format(imc)}"
        } else if (imc <= 29) {
            resposta = "Sobrepeso\nIMC: ${formatoDecimal.format(imc)}"
        } else if (imc <= 34) {
            resposta = "Obesidade (Grau I)\nIMC: ${formatoDecimal.format(imc)}"
        } else if (imc <= 39) {
            resposta = "Obesidade Severa (Grau II)\nIMC: ${formatoDecimal.format(imc)}"
        } else {
            resposta = "Obesidade Mórbida (Grau III)\nIMC: ${formatoDecimal.format(imc)}"
        }
        return resposta
    }
}