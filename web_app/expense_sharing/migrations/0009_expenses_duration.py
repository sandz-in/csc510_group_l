# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('expense_sharing', '0008_auto_20160303_0129'),
    ]

    operations = [
        migrations.AddField(
            model_name='expenses',
            name='duration',
            field=models.FloatField(null=True),
        ),
    ]
