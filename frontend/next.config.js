const path = require('path')

module.exports = {
  async headers () {
    return [
      {
        // matching all API routes
        source: '/:path*',
        headers: [
          { key: 'Access-Control-Allow-Credentials', value: 'true' },
          { key: 'Access-Control-Allow-Origin', value: '*' },
          { key: 'Access-Control-Allow-Methods', value: 'GET,OPTIONS,PATCH,DELETE,POST,PUT' },
          { key: 'Access-Control-Allow-Headers', value: 'Authorization, X-CSRF-Token, X-Requested-With, Accept, Accept-Version, Content-Length, Content-MD5, Content-Type, Date, X-Api-Version, token' }
        ]
      }
    ]
  },
  serverRuntimeConfig: {
    PATH_API: process.env.PATH_API,
    NEXT_PUBLIC_SINGLE_PATH: process.env.NEXT_PUBLIC_SINGLE_PATH,
    NEXT_PUBLIC_ENV_LOCAL_PATH_API: process.env.NEXT_PUBLIC_ENV_LOCAL_PATH_API
  },
  sassOptions: {
    includePaths: [path.join(__dirname, 'styles')]
  },
  reactStrictMode: true,
  output: 'standalone',
  eslint: {
    dirs: ['pages', 'utilites','components','customhooks','store'], // Only run ESLint on the 'pages' and 'utils' directories during production builds (next build)
  },
}
