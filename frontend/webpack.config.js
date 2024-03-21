const path = require('path');

module.exports = {
    mode: 'production',
    entry: {
        index: ['./src/index.ts'],
        cadastroCandidato: './src/Controller/cadastroCandidato.ts',
        cadastroEmpresa: './src/Controller/cadastroEmpresa.ts',
        perfilCandidato: './src/Controller/perfilCandidatos.ts',
        perfilEmpresa: './src/Controller/perfilEmpresas.ts',
        localStorage: './src/Controller/LocalStorage.ts',
        validacoes: './src/Utils/validacoes.ts'
    },
    module: {
        rules: [
            {
                test: /\.tsx?$/,
                use: 'ts-loader',
                exclude: /node_modules/,
            },
        ],
    },
    resolve: {
        extensions: ['.tsx', '.ts', '.js'],
    },
    output: {
        filename: '[name].js',
        path: path.resolve(__dirname, 'public', 'dist'),
    },
};