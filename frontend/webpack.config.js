const path = require('path');

module.exports = {
    mode: 'development',
    entry: {
        cadastroCandidato: './src/Controller/cadastroCandidato.ts',
        cadastroEmpresa: './src/Controller/cadastroEmpresa.ts',
        perfilCandidato: './src/Controller/perfilCandidato.ts',
        perfilEmpresa: './src/Controller/perfilEmpresa.ts',
    },
    devServer: {
        static: path.join(__dirname, 'dist'),
        port: 8080,
        hot: true,
    },
    output: {
        filename: '[name].js',
        path: path.resolve(__dirname, 'public', 'dist'),
    },
    plugins: [
        new CopyPlugin({
            patterns: [
            { from: 'public' },
            ]
        })
        ],
        resolve: {
            extensions: ['.tsx', '.ts', '.js'],
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
};