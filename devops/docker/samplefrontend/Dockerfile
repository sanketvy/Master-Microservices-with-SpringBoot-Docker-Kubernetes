# Base IMAGE
FROM node:20-alpine

# Copy Code
WORKDIR /sample
COPY . .

# Resolve Dependencies for Code
RUN npm install

# Expose PORT
EXPOSE 3000

# Run Application
CMD ["npm", "start"]