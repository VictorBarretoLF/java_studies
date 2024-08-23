import { Storage } from "@google-cloud/storage";
import fs from "fs";
import Ffmpeg from "fluent-ffmpeg";

const storage = new Storage();

const rawVideoBucketName = "victor-bucket-video-storage-921";
const processedVideoBucketName = "victor-bucket-processed-videos-921";

const localRawVideoPath = "./raw-videos";
const localProcessVideoPath = "./processed-videos";

/*
 * Creates the local directories for raw and processed videos.
 */
export function setupDirectories() {}

/**
 * Converts a video file from a raw format to a processed format.
 *
 * @param rawVideoName - The name of the file to convert from, located at `localRawVideoPath`.
 * @param processedVideoName - The name of the file to save the converted video to, located at `localProcessedVideoPath`.
 * @returns A promise that resolves when the video has been successfully converted.
 */
export function convertVideo(rawVideoName: string, processedVideoName: string) {
    return new Promise<void>((resolve, reject) => {
        Ffmpeg(`${localRawVideoPath}/${rawVideoName}`)
            .outputOptions("-vf", "scale=-1:360")
            .on("end", () => {
                console.log("Processing finished sucessfully");
                resolve();
            })
            .on("error", (err) => {
                console.log(`An error ocurred: ${err.message}`);
                reject(err);
            })
            .save(`${localProcessVideoPath}/${processedVideoName}`);
    });
}

/**
 * @param fileName - The name of the file to download from the {@link rawVideoBucketName} bucket into the {@link localRawVideoPath} folder.
 * @returns A promise that resolves when the file has been downloaded.
 */
export async function downloadRawVideo(fileName: string) {}

/**
 * @param fileName - The name of the file to upload from the {@link localProcessedVideoPath} folder into the {@link processedVideoBucketName}.
 * @returns A promise that resolves when the file has been uploaded.
 */
export async function uploadProcessedVideo(fileName: string) {}
