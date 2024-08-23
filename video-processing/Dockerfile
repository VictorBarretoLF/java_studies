# Using a multi-stage build to keep the final image clean and secure
FROM --platform=linux/amd64 python:3.10.4-slim-bullseye

WORKDIR /app

ENV PORT 8080
EXPOSE 8080

COPY . .

RUN pip install -r requirements.txt

CMD ["tail", "-f", "/dev/null"]