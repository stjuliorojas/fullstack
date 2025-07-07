# Etapa 1: Construcción del proyecto Angular
FROM node:20-alpine as build
WORKDIR /app
COPY . .
RUN npm install
RUN npm run build --prod

# Etapa 2: Servidor NGINX
FROM nginx:stable-alpine
COPY --from=build /app/dist/app-front /usr/share/nginx/html
EXPOSE 80

