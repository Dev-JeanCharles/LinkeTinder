const path = require('path');

module.exports = {
    mode: 'development',
    entry: {
        index: ['./src/index.ts'],
        cadastroCandidato: './src/Controller/cadastroCandidato.ts',
        cadastroEmpresa: './src/Controller/cadastroEmpresa.ts',
        perfilCandidato: './src/Controller/perfilCandidato.ts',
        perfilEmpresa: './src/Controller/perfilEmpresa.ts',
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