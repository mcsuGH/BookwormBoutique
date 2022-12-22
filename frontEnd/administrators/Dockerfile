# pull official base image
FROM node:18.12-alpine

# set working directory
WORKDIR /administrators

# add `/app/node_modules/.bin` to $PATHs
ENV PATH /administrators/node_modules/.bin:$PATH

# install app dependencies
COPY package.json ./
COPY package-lock.json ./
RUN npm install

# add app
COPY . ./

# start app
CMD ["npm", "start"]
