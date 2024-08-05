#function for generating images executed in aws lambda.
import base64
import io
import json
import os
import sys
import random
import boto3
import botocore

boto3_bedrock = boto3.client('bedrock-runtime')
def lambda_handler(event, context):
        boto3_bedrock = boto3.client('bedrock-runtime')

        txt_prompt=event["key1"]
        payload = {
        "prompt": f"\n\nHuman:give better prompt for image generation using the below prompt in such a way that it gives realistic images and if its a character description try to provide character name in prompt generated :{txt_prompt}\n\nAssistant:",
        "max_tokens_to_sample": 512,
        "temperature": 0.8,
        "top_p": 0.8,
        }
        body = json.dumps(payload)
        model_id = "anthropic.claude-v2"
        response = boto3_bedrock.invoke_model(
        body=body,
        modelId=model_id,
        accept="application/json",
        contentType="application/json",
        )

        response_body = json.loads(response.get("body").read())
        response_text = response_body.get("completion")
        prompt = response_text
        style_preset = "photographic"
        clip_guidance_preset = "FAST_GREEN"
        sampler = "K_DPMPP_2S_ANCESTRAL"
        request = json.dumps({
        "text_prompts": (
        [{"text": prompt, "weight": 1.0}]

        ),
        "cfg_scale": 10,
        "seed": random.randint(0,100000),
        "steps": 40,
        "sampler": sampler,

        })
        modelId = "stability.stable-diffusion-xl-v1"

        response = boto3_bedrock.invoke_model(body=request, modelId=modelId)
        response_body = json.loads(response.get("body").read())
        image_2_b64_str = response_body["artifacts"][0].get("base64")
        answer= image_2_b64_str
        return answer